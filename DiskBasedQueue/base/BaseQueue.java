// Copyright (c) Docugami, Inc. All rights reserved.
package docugami.takehome.base;

public abstract class BaseQueue<T> implements SimpleQueue<T> {
    public final int maxInMemory;

    protected BaseQueue(int maxInMemory) {
        this.maxInMemory = maxInMemory;
    }

    public abstract int getInMemoryCount();

    public abstract int getOnDiskCount();
}
