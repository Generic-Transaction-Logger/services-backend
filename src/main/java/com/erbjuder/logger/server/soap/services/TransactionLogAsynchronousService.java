/* 
 * Copyright (C) 2014 erbjuder.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.erbjuder.logger.server.soap.services;

import com.generic.global.transactionlogger.Transactions;
import com.erbjuder.logger.server.facade.impl.LogMessageFacadeImpl;
import com.erbjuder.logger.server.facade.interfaces.LogMessageFacade;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceException;
/**
 *
 * @author Stefan Andersson
 */
@WebService(
        serviceName = "TransactionLogAsynchronousService",
        portName = "TransactionLogAsynchronous_InPort",
        endpointInterface = "com.generic.global.transactionlogger.TransactionLogAsynchronous",
        targetNamespace = "urn:generic.com:Global:TransactionLogger",
        wsdlLocation = "WEB-INF/wsdl/Log_Service_Asynchronous/Log_Service_Asynchronous.wsdl")
@XmlSeeAlso({
    com.generic.global.transactionlogger.ObjectFactory.class
})

public class TransactionLogAsynchronousService extends LogMessageServiceBase {

    @EJB
    private LogMessageFacadeImpl logMessageFacade;
    @Resource
    private WebServiceContext jaxwsContext;
    private static final Logger logger = Logger.getLogger(TransactionLogAsynchronousService.class.getName());

    public void persist(
            @WebParam(name = "Transactions", targetNamespace = "urn:generic.com:Global:TransactionLogger", partName = "Transactions") Transactions transactions) throws WebServiceException {
        //logger.log(Level.SEVERE, "[ Got transaction log asynchronous event ] ");
        super.create(transactions);
    }

    @Override
    public LogMessageFacade getLogMessageFacade() {
        return logMessageFacade;
    }

    @Override
    public WebServiceContext getWebServiceContext() {
        return jaxwsContext;
    }
}
