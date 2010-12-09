package org.wicketstuff.mergedresources.versioning;

import java.net.URL;

import org.apache.wicket.util.string.Strings;

public abstract class AbstractClasspathResourceVersionProvider implements IResourceVersionProvider {

	public final AbstractResourceVersion getVersion(Class<?> scope, String file) throws VersionException {

		final URL url = toURL(scope, file);
		if (url == null) {
			throw new VersionException(scope, file, "can't find file " + file + " for scope + " + scope);
		}
		return getVersion(url);
	}

	protected abstract AbstractResourceVersion getVersion(URL url) throws VersionException;

	/**
	 * @return may return null, which will cause a VersionException
	 */
	protected URL toURL(final Class<?> scope, final String file) {

		final String path = getResourcePath(scope, file);

		URL url = scope.getClassLoader().getResource(path);
		if (url == null) {
			url = Thread.currentThread().getContextClassLoader().getResource(path);
		}
		return url;
	}

	protected String getResourcePath(final Class<?> scope, final String fileName) {
		final String file = Strings.beforeLast(scope.getName(), '.').replace('.', '/') + "/" + fileName;
		return file;
	}
}