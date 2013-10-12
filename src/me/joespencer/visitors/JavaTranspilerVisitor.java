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
package me.joespencer.visitors;

import me.joespencer.guides.AnnotationGuide;
import me.joespencer.guides.ClassGuide;
import me.joespencer.guides.EnumGuide;
import me.joespencer.guides.InterfaceGuide;

/**
 *
 * @author Joseph Spencer
 */
public interface JavaTranspilerVisitor {
   void visitAnnotationGuide(AnnotationGuide guide);
   void visitClassGuide(ClassGuide guide);
   void visitEnumGuide(EnumGuide guide);
   void visitInterfaceGuide(InterfaceGuide guide);
}
