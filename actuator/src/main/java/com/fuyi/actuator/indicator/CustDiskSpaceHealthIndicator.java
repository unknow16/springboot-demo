package com.fuyi.actuator.indicator;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class CustDiskSpaceHealthIndicator extends AbstractHealthIndicator {
	
	private final FileStore fileStore;
    private final long thresholdBytes;

    @Autowired
    public CustDiskSpaceHealthIndicator(
    		@Value("${health.filestore.path:.}") String path, 
    		@Value("${health.filestore.threshold.bytes:10485760}") long thresholdBytes)  throws IOException {
    	this.fileStore = Files.getFileStore(Paths.get(path));
    	this.thresholdBytes = thresholdBytes;
    }
    
	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		long diskFreeInBytes = fileStore.getUnallocatedSpace();
		if(diskFreeInBytes >= this.thresholdBytes) {
			builder.up();
		}
		
		long totalSpaceInBytes = fileStore.getTotalSpace();
		builder.withDetail("total", totalSpaceInBytes/1024/1024/1024 + " G");
		builder.withDetail("free", diskFreeInBytes/1024/1024/1024 + " G");
		builder.withDetail("threshold", this.thresholdBytes/1024/1024 + " M");
	}

}
