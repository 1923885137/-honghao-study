package DbDao;

public class UserFileInfo {
    private String file_name;
    private String file_text;
    private String file_sort;
    private String file_time;
    private String file_realName;

    public String getFile_time() {
        return file_time;
    }

    public String getFile_realName() {
        return file_realName;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getFile_text() {
        return file_text;
    }

    public String getFile_sort() {
        return file_sort;
    }

    public void setFile_time(String file_time) {
        this.file_time = file_time;
    }


    public void setFile_realName(String file_realName) {
        this.file_realName = file_realName;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setFile_text(String file_text) {
        this.file_text = file_text;
    }

    public void setFile_sort(String file_sort) {
        this.file_sort = file_sort;
    }

    public Object[] getValueArray(int id){
        return new Object[]{null,id,getFile_name(),getFile_text(),getFile_sort(),getFile_time(),getFile_realName()};
    }

    public static String[] getFieldWithID(){
        return new String[]{"id","user_id","file_name","file_text","file_sort","file_time","file_realName"};
    }
    public static String[] getFieldWithoutID(){
        return new String[]{"file_name","file_text","file_sort","file_time","file_realName"};
    }
}
