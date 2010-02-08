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

import java.util.Iterator;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.archiver.manager.ArchiverManager;

/**
 * Unpacks NAR files. Unpacking happens in the local repository, and also sets flags on binaries and corrects static
 * libraries.
 * 
 * @goal nar-unpack
 * @phase process-sources
 * @requiresProject
 * @requiresDependencyResolution test
 * @author Mark Donszelmann
 */
public class NarUnpackMojo
    extends AbstractCompileMojo
{

    /**
     * List of classifiers which you want unpack. Example ppc-MacOSX-g++, x86-Windows-msvc, i386-Linux-g++.
     * 
     * @parameter expression=""
     */
    private List classifiers;

    /**
     * To look up Archiver/UnArchiver implementations
     * 
     * @component role="org.codehaus.plexus.archiver.manager.ArchiverManager"
     * @required
     */
    private ArchiverManager archiverManager;

    public final void narExecute()
        throws MojoExecutionException, MojoFailureException
    {
        List narArtifacts = getNarManager().getNarDependencies( "compile" );
        unpackNars(narArtifacts);
        List testNarArtifacts = getNarManager().getNarDependencies( "test" );
        unpackNars(testNarArtifacts);
    }

    private void unpackNars(List narArtifacts)
        throws MojoExecutionException, MojoFailureException
    {
        if ( classifiers == null )
        {
            getNarManager().unpackAttachedNars( narArtifacts, archiverManager, null, getOS(), getLayout(), getUnpackDirectory() );
        }
        else
        {
            for ( Iterator j = classifiers.iterator(); j.hasNext(); )
            {
                getNarManager().unpackAttachedNars( narArtifacts, archiverManager, (String) j.next(), getOS(), getLayout(), getUnpackDirectory() );
            }
        }
    }
}
