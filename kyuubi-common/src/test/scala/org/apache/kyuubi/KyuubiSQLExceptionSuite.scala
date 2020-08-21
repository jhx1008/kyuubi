/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kyuubi

class KyuubiSQLExceptionSuite extends KyuubiFunSuite {

  test("KyuubiSQLException") {
    val msg0 = "this is just a dummy msg 0"
    val msg1 = "this is just a dummy msg 1"
    val msg2 = "this is just a dummy msg 2"

    val e0 = new RuntimeException(msg0)
    val e1 = new KyuubiException(msg1, e0)
    val e2 = new KyuubiSQLException(msg2, e1)
    assert(e2.toTStatus === KyuubiSQLException.toTStatus(e2))
    val e3 = KyuubiSQLException(e2.toTStatus)
    assert(e3.getMessage === e2.getMessage)
    assert(e3.getStackTrace === e2.getStackTrace)
    assert(e3.getCause.getMessage === e1.getMessage)
    assert(e3.getCause.getCause.getMessage === e0.getMessage)
  }

}