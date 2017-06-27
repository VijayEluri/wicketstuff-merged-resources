/**
 * Copyright 2016 Molindo GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 *
 */
package org.wicketstuff.mergedresources.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.wicketstuff.mergedresources.ResourceMount;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CssContribution {

	/**
	 * file names relative to annotated type. type.getClass().getSimpleName() + ".css" if emtpy
	 *
	 * @see CssContribution#media()
	 */
	String[] value() default "";

	/**
	 * css <a href="http://www.w3.org/TR/CSS2/media.html#media-types">media type</a>
	 *
	 * influences default path and default value, e.g. type.getClass().getSimpleName() + "-print.css" (value) and
	 * "print.css" (path) respectively
	 */
	String media() default "";

	/**
	 * the mount path: if it starts with '/' it's treated as an absolute path, relative to
	 * {@link ResourceMount#setPath(String)} otherwise.
	 */
	String path() default "";

	/**
	 * merged resources are sorted by their order value, starting with the highest, ending with the loweset
	 */
	int order() default 0;
}
