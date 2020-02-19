package com.mshlab.quickaid.Obj;

import android.support.annotation.NonNull;

import java.util.List;

public class ResponeSearchPlcaes {


    private ResponseEntity response;
    private MetaEntity meta;

    public ResponseEntity getResponse() {
        return response;
    }

    public MetaEntity getMeta() {
        return meta;
    }

    public static class ResponseEntity {
        private List<VenuesEntity> venues;

        public List<VenuesEntity> getVenues() {
            return venues;
        }
    }

    public static class VenuesEntity implements Comparable<VenuesEntity> {
        private VenuepageEntity venuepage;
        private List<CategoriesEntity> categories;
        private LocationEntity location;
        private String name;
        private String id;

        public VenuepageEntity getVenuepage() {
            return venuepage;
        }

        public List<CategoriesEntity> getCategories() {
            return categories;
        }

        public LocationEntity getLocation() {
            return location;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        @Override
        public int compareTo(@NonNull VenuesEntity o) {
            return 0;
        }
    }

    public static class VenuepageEntity {
        private String id;

        public String getId() {
            return id;
        }
    }

    public static class CategoriesEntity {
        private boolean primary;
        private IconEntity icon;
        private String shortname;
        private String pluralname;
        private String name;
        private String id;

        public boolean getPrimary() {
            return primary;
        }

        public IconEntity getIcon() {
            return icon;
        }

        public String getShortname() {
            return shortname;
        }

        public String getPluralname() {
            return pluralname;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }
    }

    public static class IconEntity {
        private String suffix;
        private String prefix;

        public String getSuffix() {
            return suffix;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    public static class LocationEntity {
        private List<String> formattedaddress;
        private String country;
        private String state;
        private String city;
        private String cc;
        private String postalcode;
        private int distance;
        private List<LabeledlatlngsEntity> labeledlatlngs;
        private double lng;
        private double lat;
        private String crossstreet;
        private String address;

        public List<String> getFormattedaddress() {
            return formattedaddress;
        }

        public String getCountry() {
            return country;
        }

        public String getState() {
            return state;
        }

        public String getCity() {
            return city;
        }

        public String getCc() {
            return cc;
        }

        public String getPostalcode() {
            return postalcode;
        }

        public int getDistance() {
            return distance;
        }

        public List<LabeledlatlngsEntity> getLabeledlatlngs() {
            return labeledlatlngs;
        }

        public double getLng() {
            return lng;
        }

        public double getLat() {
            return lat;
        }

        public String getCrossstreet() {
            return crossstreet;
        }

        public String getAddress() {
            return address;
        }
    }

    public static class LabeledlatlngsEntity {
        private double lng;
        private double lat;
        private String label;

        public double getLng() {
            return lng;
        }

        public double getLat() {
            return lat;
        }

        public String getLabel() {
            return label;
        }
    }

    public static class MetaEntity {
        private String requestid;
        private int code;

        public String getRequestid() {
            return requestid;
        }

        public int getCode() {
            return code;
        }
    }
}
