
package com.cognitev.nearbyapp.Model.VenuesExploreModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


 public class SuggestedFilters {

    private String header;
    private List<Filter> filters = new ArrayList<Filter>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The header
     */
    public String getHeader() {
        return header;
    }

    /**
     * 
     * @param header
     *     The header
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * 
     * @return
     *     The filters
     */
    public List<Filter> getFilters() {
        return filters;
    }

    /**
     * 
     * @param filters
     *     The filters
     */
    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
