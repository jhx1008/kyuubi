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

package yaooqinn.kyuubi.auth

import javax.security.sasl.AuthenticationException

import org.apache.spark.SparkConf
import yaooqinn.kyuubi.auth.AuthMethods.AuthMethods

import org.apache.kyuubi.service.authentication.{AnonymousAuthenticationProviderImpl, PasswdAuthenticationProvider}

/**
 * This class helps select a [[PasswdAuthenticationProvider]] for a given [[AuthMethods]]
 */
object AuthenticationProviderFactory {
  @throws[AuthenticationException]
  def getAuthenticationProvider(
      method: AuthMethods,
      conf: SparkConf): PasswdAuthenticationProvider = method match {
    case AuthMethods.NONE => new AnonymousAuthenticationProviderImpl
    case AuthMethods.LDAP => new LdapAuthenticationProviderImpl(conf)
    case _ => throw new AuthenticationException("Not a valid authentication method")
  }
}
