public class longPower {

   /* Approach :   The following code will run and it will cache  all power numbers, less than the given index.   At very first step it will calculate all the powers which are smaller than given index and store it in HashMap.  A priority queue is used to retrieve the minimum element. A hashset is used to store all powers (useful for the case 16 which will be generated twice )
*/

	static HashMap<Long, Long> pMap = new HashMap<Long, Long>();
	 static long lastX = 0;
	 static long lastY = 0;
	 static PriorityQueue<Long> pQueue=new PriorityQueue<Long>();
	 static Set<Long> pSet = new HashSet<Long>(4);
	 static long X = 2;
	 static long Y = 2;
	 static long pCounter  = 1;

		 
	 public static long powerN(long number, long power) {
		
		   long result1 = number;
		   while(power > 1) {
		      result1 *=number;
		      power--;
		   }
		   return result1;
		}
	 
	 
	 static long getPowerNumber(long index)  {

		Long power;
		//hashMap is used for caching and reuse.
		//if same index is called multiple times will be faster of O(1)
		
		
		if ( pMap.containsKey(index)) 
			return pMap.get(index);
		
		//if (index  == 0) {
			pSet.add((long) 4);
			pMap.put((long)0,  (long)4);
		//}
		
		for(long i = lastX; i < index; i ++) {
			for(long j = lastY; j < index; j ++) {
				power = powerN(( X + i) , (Y + j));
				
				if (pSet.contains(power))
					continue;
				pSet.add(power);
				pQueue.offer(power);
			}	
		}
		
		long output = 0;
		for(long i = pCounter; i < index; i++) {
			output =  pQueue.poll();
			pMap.put(i, output);
		}
		

		lastX = index;
		lastY = index;
		pCounter = index;
		
	    return output;
	 }
}
