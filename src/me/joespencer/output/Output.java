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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joseph Spencer
 */
public class Output {
   private final List output;

   public Output() {
      this.output = new ArrayList();
   }

   public void append(Object obj){
      if(obj == null){
         throw new NullPointerException("can't append null");
      }
      output.add(obj);
   }

   public void prepend(Object obj){
      if(obj == null){
         throw new NullPointerException("can't prepend null");
      }
      output.add(0, obj);
   }

   @Override
   public String toString(){
      StringBuilder builder=new StringBuilder();
      for(Object obj:output){
         builder.append(obj.toString());
      }
      return builder.toString();
   }

}
