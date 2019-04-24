package org.acme.listeners;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventContext;
import org.nuxeo.ecm.core.event.EventListener;
import org.nuxeo.ecm.core.event.EventProducer;
import org.nuxeo.ecm.core.event.impl.DocumentEventContext;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.transaction.TransactionHelper;

public class CancelDocumentModifiedListener implements EventListener {

    private static final Log log = LogFactory.getLog(CancelDocumentModifiedListener.class);
  
    public static final String DISABLE_ALL_DOCUMENT_MODIFIED_LISTENERS = "disableAllDocumentModifiedListeners";
    public static final String DOCUMENT_MODIFIED_CANCELED = "documentModifiedCanceled";

    @Override
    public void handleEvent(Event event) {
        EventContext ctx = event.getContext();
        if (!(ctx instanceof DocumentEventContext)) {
            return;
        }
        if (complies(event)) {
            if (log.isDebugEnabled()) {
                log.debug("Canceling all 'documentModified' listeners... ");
            }
//            TransactionHelper.setTransactionRollbackOnly();
            DocumentEventContext docCtx = (DocumentEventContext) ctx;
            event.cancel();
            Event newEvent = docCtx.newEvent(DOCUMENT_MODIFIED_CANCELED);
            Framework.getService(EventProducer.class).fireEvent(newEvent);
        }
    }
    
    /**
     * @param event
     * @return <code>true</code> if event 'documentModified' must be canceled, otherwise <code>false</code>
     */
    protected boolean complies(Event event) {
        Boolean block = (Boolean) event.getContext().getProperty(DISABLE_ALL_DOCUMENT_MODIFIED_LISTENERS);
        return (block != null && block.booleanValue());
    }
}
