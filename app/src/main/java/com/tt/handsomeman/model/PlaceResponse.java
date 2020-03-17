package com.tt.handsomeman.model;

public class PlaceResponse {
    private String placeId;
    private String primaryPlaceName;
    private String secondaryPlaceName;

    public PlaceResponse(String placeId, String primaryPlaceName, String secondaryPlaceName) {
        this.placeId = placeId;
        this.primaryPlaceName = primaryPlaceName;
        this.secondaryPlaceName = secondaryPlaceName;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPrimaryPlaceName() {
        return primaryPlaceName;
    }

    public void setPrimaryPlaceName(String primaryPlaceName) {
        this.primaryPlaceName = primaryPlaceName;
    }

    public String getSecondaryPlaceName() {
        return secondaryPlaceName;
    }

    public void setSecondaryPlaceName(String secondaryPlaceName) {
        this.secondaryPlaceName = secondaryPlaceName;
    }
}
