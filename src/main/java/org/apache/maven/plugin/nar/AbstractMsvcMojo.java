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
 * Abstract Visual Studio Mojo keeps configuration
 * 
 * @author Mark Donszelmann
 */
public abstract class AbstractMsvcMojo
    extends AbstractResourcesMojo
{
    /**
     * .sln file
     *
     * @parameter expression="${project.artifactId}.sln"
     */
    private File slnFile;

    /**
     * Source directory for Visual Studio style project.  Should contain <code>slnFile</code>.
     * 
     * @parameter expression="${basedir}/src/msvc"
     * @required
     */
    private File msvcSourceDirectory;

    /**
     * Directory to which Visual Studio sources are copied and "configured"
     * 
     * @parameter expression="${project.build.directory}/nar/msvc"
     * @required
     */
    private File msvcTargetDirectory;

    /**
     * @return
     * @throws MojoFailureException
     * @throws MojoExecutionException 
     */
    protected final File getMsvcAOLSourceDirectory()
        throws MojoFailureException, MojoExecutionException
    {
        return new File( getMsvcAOLDirectory(), "src" );
    }

    /**
     * @return
     * @throws MojoFailureException
     * @throws MojoExecutionException 
     */
    protected final File getMsvcAOLTargetDirectory()
        throws MojoFailureException, MojoExecutionException
    {
        return new File( getMsvcAOLSourceDirectory().getParentFile(), "target" );
    }
    
    protected final File getMsvcSlnFile() {
        return slnFile;
    }

    protected final File getMsvcSourceDirectory() {
        return msvcSourceDirectory;
    }

    /**
     * @return
     * @throws MojoFailureException
     * @throws MojoExecutionException 
     */
    private File getMsvcAOLDirectory()
        throws MojoFailureException, MojoExecutionException
    {
        return new File( msvcTargetDirectory, getAOL().toString() );
    }
}
