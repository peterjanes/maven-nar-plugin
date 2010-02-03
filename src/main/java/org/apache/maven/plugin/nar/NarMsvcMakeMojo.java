package org.apache.maven.plugin.nar;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Runs <code>devenv /make</code> on the Visual Studio .sln file
 * 
 * @goal nar-msvc-make
 * @phase compile
 * @requiresProject
 * @author Mark Donszelmann
 */
public class NarMsvcMakeMojo
    extends AbstractMsvcMojo
{
    public final void narExecute()
        throws MojoExecutionException, MojoFailureException
    {
        if ( !OS.WINDOWS.equals(NarUtil.getOS( null )) )
        {
            return;
        }

        getLog().info( "Running devenv on " + getMsvcSlnFile().getPath() );
        int result = NarUtil.runCommand( "devenv", new String[] { getMsvcSlnFile().getPath(), "/make" }, null, null, getLog() );
        if ( result != 0 )
        {
            throw new MojoExecutionException( "'devenv' errorcode: " + result );
        }
    }
}
