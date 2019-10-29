package org.task.services.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.task.services.model.ColumnStatistics;
import org.task.services.model.TableStatistics;
import org.task.services.util.ComputeUtil;

/**
 * Task3 : The repository class which communicates with database for statistics about each column and each table.
 * @author Manu Mohan
 *
 */
@Repository
public class TableDataRepository implements ITableDataRepository{


	@Autowired
	private JdbcTemplate template;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ColumnStatistics getColumnStatistics(String tableName, String columnName) {

		ColumnStatistics columnStatistics = new ColumnStatistics();
		columnStatistics.setMaxValue(getMaxValue(tableName, columnName));
		columnStatistics.setMinValue(getMinValue(tableName, columnName));
		columnStatistics.setAvgValue(getAverageValue(tableName, columnName));
		columnStatistics.setMedianValue(getMedianValue(tableName, columnName));

		return columnStatistics;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMaxValue(String tableName, String columnName) {

		String query = "SELECT MAX("+columnName+") from "+tableName;
		return template.queryForObject(query, String.class);

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMinValue(String tableName, String columnName) {

		String query = "SELECT MIN("+columnName+") from "+tableName;
		return template.queryForObject(query, String.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAverageValue(String tableName, String columnName) {
		String query = "SELECT AVG("+columnName+") from "+tableName;
		return template.queryForObject(query, String.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMedianValue(String tableName, String columnName) {

		String query = "SELECT "+columnName+" from "+tableName;

		List<Long> datas = template.query(query,  new RowMapper<Long>(){
			@Override
			public Long mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getLong(1);
			}
		});

		return ComputeUtil.getInstance().getMedianValue(datas);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNumberOfRecordsInTable(String tableName) {
		String query = "SELECT count(*) from "+tableName;
		return template.queryForObject(query, String.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getNumberOfAttributesInTable(String tableName) {

		return template.execute(new ConnectionCallback<Integer>() {

			@Override
			public Integer doInConnection(Connection connection) throws SQLException, DataAccessException {

				int size = 0;
				DatabaseMetaData metaData = connection.getMetaData();

				ResultSet columns = metaData.getColumns(null,  null,  tableName, "%");

				if(columns != null) {
					columns.last();
					size = columns.getRow();
				}
				return size;
			}

		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TableStatistics getTableStatistics(String tableName) {

		TableStatistics tableStatistics = new TableStatistics();
		tableStatistics.setAttributeCount(getNumberOfAttributesInTable(tableName));
		tableStatistics.setRecordCount(getNumberOfRecordsInTable(tableName));

		return tableStatistics;
	}

}
