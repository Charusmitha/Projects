package docugami.takehome.tests;

import org.junit.Assert;
import org.junit.Test;
import docugami.takehome.implementation.FileBasedImplementation;

public class DiskTests {
	
	public DiskTests() {
        
    }
	
	@Test
	public void testReadAndWriteFromDisk() {
		FileBasedImplementation<Integer> disk = new FileBasedImplementation<Integer>(Integer.class, 2);
        disk.copyToFile(8);
        disk.copyToFile(9);
        Integer[] arr = disk.copyFromFile();
        Assert.assertEquals((int)arr[0], 8);
        Assert.assertEquals((int)arr[1], 9);
    }
	
	@Test
	public void testOnDiskCount() {
        FileBasedImplementation<Integer> disk = new FileBasedImplementation<Integer>(Integer.class, 2);
        disk.copyToFile(8);
        disk.copyToFile(9);
        Assert.assertEquals(2, disk.getOnDiskCount());
	}

}
