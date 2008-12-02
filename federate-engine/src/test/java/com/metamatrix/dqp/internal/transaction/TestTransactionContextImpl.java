/*
 * JBoss, Home of Professional Open Source.
 * Copyright (C) 2008 Red Hat, Inc.
 * Copyright (C) 2000-2007 MetaMatrix, Inc.
 * Licensed to Red Hat, Inc. under one or more contributor 
 * license agreements.  See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

package com.metamatrix.dqp.internal.transaction;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.xa.XAResource;

import junit.framework.TestCase;

import com.metamatrix.core.MetaMatrixRuntimeException;
import com.metamatrix.core.util.UnitTestUtil;

public class TestTransactionContextImpl extends TestCase {
    
    public void testSerialization() throws Exception {
        TransactionContextImpl context = new TransactionContextImpl();
        
        UnitTestUtil.helpSerialize(context);
        
        context.setTransaction(new Transaction() {

            public void commit() throws RollbackException,
                                HeuristicMixedException,
                                HeuristicRollbackException,
                                SecurityException,
                                IllegalStateException,
                                SystemException {
            }

            public boolean delistResource(XAResource arg0,
                                          int arg1) throws IllegalStateException,
                                                   SystemException {
                return false;
            }

            public boolean enlistResource(XAResource arg0) throws RollbackException,
                                                          IllegalStateException,
                                                          SystemException {
                return false;
            }

            public int getStatus() throws SystemException {
                return 0;
            }

            public void registerSynchronization(Synchronization arg0) throws RollbackException,
                                                                     IllegalStateException,
                                                                     SystemException {
            }

            public void rollback() throws IllegalStateException,
                                  SystemException {
            }

            public void setRollbackOnly() throws IllegalStateException,
                                         SystemException {
            }
            
        }, "foo"); //$NON-NLS-1$
        
        try {
            UnitTestUtil.helpSerialize(context);
            fail("expected exception"); //$NON-NLS-1$
        } catch (MetaMatrixRuntimeException e) {
            
        }
    }

}
