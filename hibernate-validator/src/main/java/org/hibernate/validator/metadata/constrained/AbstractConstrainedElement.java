/*
* JBoss, Home of Professional Open Source
* Copyright 2011, Red Hat, Inc. and/or its affiliates, and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.hibernate.validator.metadata.constrained;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.validator.metadata.MetaConstraint;
import org.hibernate.validator.metadata.location.ConstraintLocation;

/**
 * @author Gunnar Morling
 */
public abstract class AbstractConstrainedElement implements ConstrainedElement {

	private final EnumSet<ConfigurationSource> sources;

	private final ConstraintLocation location;

	private final Set<MetaConstraint<?>> constraints;

	private final boolean isCascading;

	public AbstractConstrainedElement(ConfigurationSource source, ConstraintLocation location, Set<MetaConstraint<?>> constraints, boolean isCascading) {

		this( EnumSet.of( source ), location, constraints, isCascading );
	}

	/**
	 * @param constraints
	 * @param isCascading
	 */
	public AbstractConstrainedElement(EnumSet<ConfigurationSource> sources, ConstraintLocation location, Set<MetaConstraint<?>> constraints, boolean isCascading) {

		this.sources = sources;
		this.location = location;
		this.constraints = constraints != null ? constraints : Collections.<MetaConstraint<?>>emptySet();
		this.isCascading = isCascading;
	}

	public EnumSet<ConfigurationSource> getSources() {
		return sources;
	}

	public ConstraintLocation getLocation() {
		return location;
	}

	public Iterator<MetaConstraint<?>> iterator() {
		return constraints.iterator();
	}

	public Set<MetaConstraint<?>> getConstraints() {
		return constraints;
	}

	public boolean isCascading() {
		return isCascading;
	}

	public boolean isConstrained() {
		return isCascading || !constraints.isEmpty();
	}

	@Override
	public String toString() {
		return "AbstractConstrainedElement [sources=" + sources + ", location="
				+ location + ", constraints=" + constraints + ", isCascading="
				+ isCascading + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ( ( location == null ) ? 0 : location.hashCode() );
		result = prime * result + ( ( sources == null ) ? 0 : sources.hashCode() );
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		AbstractConstrainedElement other = (AbstractConstrainedElement) obj;
		if ( location == null ) {
			if ( other.location != null ) {
				return false;
			}
		}
		else if ( !location.equals( other.location ) ) {
			return false;
		}
		if ( sources == null ) {
			if ( other.sources != null ) {
				return false;
			}
		}
		else if ( !sources.equals( other.sources ) ) {
			return false;
		}
		return true;
	}


}