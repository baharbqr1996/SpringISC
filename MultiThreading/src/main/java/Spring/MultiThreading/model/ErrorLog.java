package Spring.MultiThreading.model;

import java.time.LocalDateTime;

public class ErrorLog {
    private String fileName; // نام فایل
    private int recordNumber; // شماره رکورد
    private String errorCode; // کد خطا
    private String errorClassificationName; // نام دسته‌بندی خطا
    private String errorDescription; // توصیف خطا
    private LocalDateTime errorDate; // تاریخ و زمان خطا
	
    public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorClassificationName() {
		return errorClassificationName;
	}
	public void setErrorClassificationName(String errorClassificationName) {
		this.errorClassificationName = errorClassificationName;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public LocalDateTime getErrorDate() {
		return errorDate;
	}
	public void setErrorDate(LocalDateTime errorDate) {
		this.errorDate = errorDate;
	}

    
}

