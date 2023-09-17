package com.scaler.lld.design.assignments.singleton;

public class FileBasedConfigurationManagerImpl extends FileBasedConfigurationManager {

    private static FileBasedConfigurationManagerImpl INSTANCE = null;

    private FileBasedConfigurationManagerImpl(){
        super();
    }

    @Override
    public String getConfiguration(String key) {
        if(INSTANCE!=null) {
           return INSTANCE.getProperties().getProperty(key);
        }else {
            throw new NullPointerException("Configuration not exist for " + key);
        }
    }

    @Override
    public <T> T getConfiguration(String key, Class<T> type) {
        if(INSTANCE!=null) {
            String str = INSTANCE.properties.getProperty(key);
            if (str != null)
                return convert(str, type);
            else
                return null;

        }else{
            throw new NullPointerException("Configuration not present for " + key + " and " + type);
        }
    }

    @Override
    public void setConfiguration(String key, String value) {
        if(INSTANCE!=null) {
            INSTANCE.getProperties().setProperty(key,value);
        }else{
            throw new NullPointerException("Not able to set configuration for "+ key + " and " + value);
        }
    }

    @Override
    public <T> void setConfiguration(String key, T value) {
        if(INSTANCE!=null) {

        }else{
            throw new NullPointerException("Not able to set configuration for "+ key + " and " + value);
        }
    }

    @Override
    public void removeConfiguration(String key) {
        if(INSTANCE!=null) {
            INSTANCE.getProperties().remove(key);
        }else{
            throw new NullPointerException("Not able to remove configuration for key " + key);
        }
    }

    @Override
    public void clear() {
       if(INSTANCE!=null && INSTANCE.getProperties()!=null){
            INSTANCE.getProperties().clear();
       }else{
           throw new NullPointerException("Not able to clear properties");
       }
    }

    public static FileBasedConfigurationManager getInstance() {
            if (INSTANCE == null) {
                synchronized (FileBasedConfigurationManagerImpl.class) {
                    if(INSTANCE==null) {
                        INSTANCE = new FileBasedConfigurationManagerImpl();
                    }
                }
            }
        return INSTANCE;
    }

    public static void resetInstance() {
        INSTANCE=null;
    }

}