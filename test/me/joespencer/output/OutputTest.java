/*
 * Copyright 2013 Joseph Spencer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.joespencer.output;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joseph Spencer
 */
public class OutputTest {
   Output output;

   @Before
   public void before() {
      output = new Output();
   }

   @Test(expected = NullPointerException.class)
   public void we_should_not_be_allowed_to_prepend_null(){
      output.prepend(null);
   }
   @Test(expected = NullPointerException.class)
   public void we_should_not_be_allowed_to_append_null(){
      output.append(null);
   }

   @Test
   public void we_should_be_able_to_prepend_and_append_objects(){
      output.append(5);
      output.prepend(6);
      assertEquals("65", output.toString());
   }
}