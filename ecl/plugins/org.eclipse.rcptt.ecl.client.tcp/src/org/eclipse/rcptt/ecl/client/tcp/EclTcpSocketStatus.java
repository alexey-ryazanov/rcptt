/*******************************************************************************
 * Copyright (c) 2009, 2014 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.ecl.client.tcp;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class EclTcpSocketStatus extends Status {

	public EclTcpSocketStatus(int severity, String pluginId, int code,
			String message, Throwable exception) {
		super(severity, pluginId, code, message, exception);
	}

	public EclTcpSocketStatus(IStatus status) {
		this(status.getSeverity(), status.getPlugin(), status.getCode(), status
				.getMessage(), status.getException());
	}

}
