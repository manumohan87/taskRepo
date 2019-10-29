package org.task.services.util;

import java.util.Collections;
import java.util.List;

/**
 * Task 3: Utility class for computations
 * @author Manu Mohan
 *
 */
public  final class ComputeUtil {

	private static ComputeUtil instance;

	private ComputeUtil() {

	}

	public static ComputeUtil getInstance() {
		synchronized (ComputeUtil.class) 
		{ 
			if(instance == null) {
				
				instance = new ComputeUtil();
			}
		}
		return instance;
	}
	
	/**
	 * Gets the median value in the data
	 * @param datas list of datas
	 * @return the median value.
	 */
	public String getMedianValue(List<Long> datas) {

		if(datas != null && !datas.isEmpty()) {
			int size = datas.size();
			Collections.sort(datas);

			if(size % 2 == 0) {
				Long medianValue = (datas.get(size/2) + datas.get(size/2 - 1))/2;
				return String.valueOf(medianValue);
			}else {
				Long medianValue = datas.get(size/2);
				return String.valueOf(medianValue);
			}

		}
		return "";
	}
}
