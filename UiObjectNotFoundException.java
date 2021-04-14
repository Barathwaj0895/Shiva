package rudhra.framework.core;

public class UiObjectNotFoundException extends Throwable {
     private static final long serialVersionUID = 1L;

        public UiObjectNotFoundException(String msg) {
            super(msg);
        }

        public UiObjectNotFoundException(String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
        }

        public UiObjectNotFoundException(Throwable throwable) {
            super(throwable);
        }
    }
