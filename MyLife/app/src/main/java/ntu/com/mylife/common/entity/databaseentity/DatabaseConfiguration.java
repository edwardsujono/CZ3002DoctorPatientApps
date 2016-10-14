package ntu.com.mylife.common.entity.databaseentity;

/**
 * Created by martinus on 14/10/16.
 */
public class DatabaseConfiguration {

    public static final String DATABASE_URL = "https://lifemate.firebaseio.com/";

    public static final String MEDICAL_RECORD = "MedicalRecords";
    public static final String CHAT = "Chat";
    public static final String MESSAGE ="Message";
    public static final String USER_SCHEDULE = "UserSchedule";
    public static final String DOCTORS = "Doctors";
    public static final String PATIENTS = "Patients";
    public static final String REMINDERS = "Reminders";

    public static final String CHAT_USER1ID = "user1Id";
    public static final String CHAT_USER2ID = "user2Id";
    public static final String CHAT_LATESTMESSAGE = "latestMessage";
    public static final String CHAT_LATESTMESSAGETIME = "latestMessageTime";

    public static final String DAYSCHEDULE_DATE = "date";
    public static final String DAYSCHEDULE_TIME = "time";
    public static final String DAYSCHEDULE_USERID = "userId";
    public static final String DAYSCHEDULE_DESCRIPTION = "description";
    public static final String DAYSCHEDULE_FUTURETIMEMILLIS = "futureTimeMillis";

    public static final String USER_FULLNAME = "fullName";
    public static final String USER_USERID = "userId";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_IMAGE = "image";

    public static final String MEDICALRECORD_TIME = "time";
    public static final String MEDICALRECORD_MEDICALRECORDDESCRIPTION = "medicalRecordDescription";
    public static final String MEDICALRECORD_DOCTORID = "doctorId";

    public static final String MESSAGE_SENDERUSERID = "senderUserId";
    public static final String MESSAGE_RECEIVERUSERID = "receiverUserId";
    public static final String MESSAGE_MESSAGE = "message";
    public static final String MESSAGE_DATE = "date";

    public static final String REMINDER_DATE = "date";
    public static final String REMINDER_NOTIFICATION = "notification";
    public static final String REMINDER_TIME = "time";
    public static final String REMINDER_DOCTORID = "doctorId";
    public static final String REMINDER_PATIENTID = "patientId";

    public static final String TIMESCHEDULE_MESSAGE = "message";
    public static final String TIMESCHEDULE_TIME = "time";

    public static final String USERMEDICALRECORDLIST_USERID = "userId";
    public static final String USERMEDICALRECORDLIST_MEDICALRECORDLIST = "medicalRecordList";

    public static final String USERSCHEDULE_USERID = "userId";
    public static final String USERSCHEDULE_DAYSCHEDULELIST = "dayScheduleList";





}
