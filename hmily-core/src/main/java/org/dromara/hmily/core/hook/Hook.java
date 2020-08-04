/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.hmily.core.hook;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * The enum Hook.
 */
public enum Hook {
    
    /**
     * Undo hook.
     */
    UNDO;
    
    private final List<Consumer<Object>> consumers = new CopyOnWriteArrayList<>();
    
    /**
     * Register.
     *
     * @param <T>      the type parameter
     * @param consumer the consumer
     */
    @SuppressWarnings("unchecked")
    public <T> void register(Consumer<T> consumer) {
        consumers.add((Consumer<Object>) consumer);
    }
    
    /**
     * Run.
     *
     * @param object the object
     */
    public void run(final Object object) {
        for (Consumer<Object> each : consumers) {
            each.accept(object);
        }
    }
    
}