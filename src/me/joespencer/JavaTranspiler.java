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
package me.joespencer;

import me.joespencer.guides.GuideFactory;
import me.joespencer.visitors.JavaTranspilerVisitor;

/**
 *
 * @author Joseph Spencer
 */
public class JavaTranspiler {
   private final GuideFactory guideFactory;
   private final Class[] classes;

   public JavaTranspiler(Class[] classes, GuideFactory guideFactory) {
      if(classes == null){
         throw new IllegalArgumentException("classes must be an array");
      }
      if(guideFactory == null){
         throw new IllegalArgumentException("guideFactory shall not be null");
      }

      if(classes.length == 0){
         throw new IllegalArgumentException("classes array must not be empty");
      }

      this.classes=classes;
      this.guideFactory=guideFactory;
   }

   public void serve(JavaTranspilerVisitor visitor){
      if(visitor == null){
         throw new IllegalArgumentException("visitor was null");
      }

      for(Class clazz:classes){
         if(clazz.isAnnotation()){
            visitor.visitAnnotationGuide(
               guideFactory.createAnnotationGuide(clazz)
            );
         } else if(clazz.isEnum()){
            visitor.visitEnumGuide(
               guideFactory.createEnumGuide(clazz)
            );
         } else if(clazz.isInterface()){
            visitor.visitInterfaceGuide(
               guideFactory.createInterfaceGuide(clazz)
            );
         } else {//it's just a plain old class
            visitor.visitClassGuide(guideFactory.createClassGuide(clazz));
         }
      }
   }
}
