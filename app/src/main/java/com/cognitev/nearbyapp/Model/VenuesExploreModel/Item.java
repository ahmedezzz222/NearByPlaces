
package com.cognitev.nearbyapp.Model.VenuesExploreModel;

import java.util.HashMap;
import java.util.Map;

 public class Item {

    private Integer unreadCount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The unreadCount
     */
    public Integer getUnreadCount() {
        return unreadCount;
    }

    /**
     * 
     * @param unreadCount
     *     The unreadCount
     */
    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
