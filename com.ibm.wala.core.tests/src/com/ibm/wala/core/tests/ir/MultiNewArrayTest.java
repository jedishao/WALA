/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.wala.core.tests.ir;

import java.io.IOException;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.core.tests.util.TestConstants;
import com.ibm.wala.core.tests.util.WalaTestCase;
import com.ibm.wala.ipa.callgraph.AnalysisCache;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.impl.Everywhere;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SSANewInstruction;
import com.ibm.wala.ssa.SSAOptions;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.config.AnalysisScopeReader;
import com.ibm.wala.util.io.FileProvider;

public class MultiNewArrayTest extends WalaTestCase {

  private static final ClassLoader MY_CLASSLOADER = MultiNewArrayTest.class.getClassLoader();
  
  public void testMultiNewArray1() throws IOException, ClassHierarchyException {
    AnalysisScope scope = null;
    scope = AnalysisScopeReader.read(TestConstants.WALA_TESTDATA, FileProvider.getFile("J2SEClassHierarchyExclusions.txt"), MY_CLASSLOADER);
    ClassHierarchy cha = ClassHierarchy.make(scope);
    IClass klass = cha.lookupClass(TypeReference.findOrCreate(ClassLoaderReference.Application, TestConstants.MULTI_DIM_MAIN));
    assertTrue(klass != null);
    IMethod m = klass.getMethod(Selector.make("testNewMultiArray()V"));
    assertTrue(m != null);
    AnalysisCache cache = new AnalysisCache();
    IR ir = cache.getIRFactory().makeIR(m, Everywhere.EVERYWHERE, new SSAOptions());
    assertTrue(ir != null);
    SSAInstruction[] instructions = ir.getInstructions();
    for (SSAInstruction instr : instructions) {
      if (instr instanceof SSANewInstruction) {
//        assertTrue(instr.getNumberOfUses() == 2);
//        assertTrue(instr.getUse(0) == 3);
//        assertTrue(instr.getUse(1) == 4);
      }
    }
  }
}
