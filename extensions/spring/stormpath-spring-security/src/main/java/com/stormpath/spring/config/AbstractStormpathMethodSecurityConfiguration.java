/*
 * Copyright 2015 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stormpath.spring.config;

import com.stormpath.spring.security.authz.permission.evaluator.WildcardPermissionEvaluator;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * @since 1.0.RC5
 */
public abstract class AbstractStormpathMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    private static final String EMPTY_ROLE_PREFIX = "";

    public PermissionEvaluator stormpathWildcardPermissionEvaluator() {
        return new WildcardPermissionEvaluator();
    }

    //Prevents the addition of the "ROLE_" prefix in authorities
    public MethodSecurityExpressionHandler stormpathMethodSecurityExpressionHandler() {
        DefaultMethodSecurityExpressionHandler methodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
        methodSecurityExpressionHandler.setPermissionEvaluator(stormpathWildcardPermissionEvaluator());
        methodSecurityExpressionHandler.setDefaultRolePrefix(EMPTY_ROLE_PREFIX);
        return methodSecurityExpressionHandler;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return stormpathMethodSecurityExpressionHandler();
    }

}
