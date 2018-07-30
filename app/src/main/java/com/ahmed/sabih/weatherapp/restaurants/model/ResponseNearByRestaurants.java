package com.ahmed.sabih.weatherapp.restaurants.model;

import java.util.List;

public class ResponseNearByRestaurants {
    public Location location;
    public Popularity popularity;
    public String link;
    public List<NearbyRestaurant> nearby_restaurants;

    public class Location
    {
        public String entity_type;
        public int entity_id;
        public String title;
        public String latitude;
        public String longitude;
        public int city_id;
        public String city_name;
        public int country_id;
        public String country_name;



        public String getEntity_type() {
            return entity_type;
        }

        public int getEntity_id() {
            return entity_id;
        }

        public String getTitle() {
            return title;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public int getCity_id() {
            return city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public int getCountry_id() {
            return country_id;
        }

        public String getCountry_name() {
            return country_name;
        }
    }

    public class Popularity
    {
        public String popularity;
        public String nightlife_index;
        public List<String> nearby_res;
        public List<String> top_cuisines;
        public String popularity_res;
        public String nightlife_res;
        public String subzone;
        public int subzone_id;
        public String city;
    }

    public class R
    {
        public int res_id;
    }

    public class UserRating
    {
        public String aggregate_rating;
        public String rating_text;
        public String rating_color;
        public String votes;


        public String getAggregate_rating() {
            return aggregate_rating;
        }

        public String getRating_text() {
            return rating_text;
        }

        public String getRating_color() {
            return rating_color;
        }

        public String getVotes() {
            return votes;
        }
    }

    public class Photo
    {
        public String url;
        public String thumb_url;
        public int order;
        public String md5sum;
        public int photo_id;
        public Object uuid;
        public String type;
    }


    public class ShareData
    {
        public int should_show;
    }

    public class Event
    {
        public int event_id;
        public String friendly_start_date;
        public String friendly_end_date;
        public String friendly_timing_str;
        public String start_date;
        public String end_date;
        public String end_time;
        public String start_time;
        public int is_active;
        public String date_added;
        public List<Photo> photos;
        public List<Object> restaurants;
        public int is_valid;
        public String share_url;
        public int show_share_url;
        public String title;
        public String description;
        public String display_time;
        public String display_date;
        public int is_end_time_set;
        public String disclaimer;
        public int event_category;
        public String event_category_name;
        public String book_link;
        public ShareData share_data;
    }

    public class ZomatoEvent
    {
        public Event event;
    }

    public class Restaurant
    {
        public R r;
        public String apikey;
        public String id;
        public String name;
        public String url;
        public Location location;
        public int switch_to_order_menu;
        public String cuisines;
        public int average_cost_for_two;
        public int price_range;
        public String currency;
        public List<Object> offers;
        public int opentable_support;
        public int is_zomato_book_res;
        public String mezzo_provider;
        public int is_book_form_web_view;
        public String book_form_web_view_url;
        public String book_again_url;
        public String thumb;
        public UserRating user_rating;
        public String photos_url;
        public String menu_url;
        public String featured_image;
        public int has_online_delivery;
        public int is_delivering_now;
        public boolean include_bogo_offers;
        public String deeplink;
        public int is_table_reservation_supported;
        public int has_table_booking;
        public String events_url;
        public List<ZomatoEvent> zomato_events;
        public int medio_provider;
        public String order_url;
        public String order_deeplink;
        public String book_url;


        public class Location{
            public String address;
            public String locality;
            public String city;
            public int city_id;
            public String latitude;
            public String longitude;
            public String zipcode;
            public int country_id;
            public String locality_verbose;


            public String getAddress() {
                return address;
            }

            public String getLocality() {
                return locality;
            }

            public String getCity() {
                return city;
            }

            public int getCity_id() {
                return city_id;
            }

            public String getLatitude() {
                return latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public String getZipcode() {
                return zipcode;
            }

            public int getCountry_id() {
                return country_id;
            }

            public String getLocality_verbose() {
                return locality_verbose;
            }
        }


        public R getR() {
            return r;
        }

        public String getApikey() {
            return apikey;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        public Location getLocation() {
            return location;
        }

        public int getSwitch_to_order_menu() {
            return switch_to_order_menu;
        }

        public String getCuisines() {
            return cuisines;
        }

        public int getAverage_cost_for_two() {
            return average_cost_for_two;
        }

        public int getPrice_range() {
            return price_range;
        }

        public String getCurrency() {
            return currency;
        }

        public List<Object> getOffers() {
            return offers;
        }

        public int getOpentable_support() {
            return opentable_support;
        }

        public int getIs_zomato_book_res() {
            return is_zomato_book_res;
        }

        public String getMezzo_provider() {
            return mezzo_provider;
        }

        public int getIs_book_form_web_view() {
            return is_book_form_web_view;
        }

        public String getBook_form_web_view_url() {
            return book_form_web_view_url;
        }

        public String getBook_again_url() {
            return book_again_url;
        }

        public String getThumb() {
            return thumb;
        }

        public UserRating getUser_rating() {
            return user_rating;
        }

        public String getPhotos_url() {
            return photos_url;
        }

        public String getMenu_url() {
            return menu_url;
        }

        public String getFeatured_image() {
            return featured_image;
        }

        public int getHas_online_delivery() {
            return has_online_delivery;
        }

        public int getIs_delivering_now() {
            return is_delivering_now;
        }

        public boolean isInclude_bogo_offers() {
            return include_bogo_offers;
        }

        public String getDeeplink() {
            return deeplink;
        }

        public int getIs_table_reservation_supported() {
            return is_table_reservation_supported;
        }

        public int getHas_table_booking() {
            return has_table_booking;
        }

        public String getEvents_url() {
            return events_url;
        }

        public List<ZomatoEvent> getZomato_events() {
            return zomato_events;
        }

        public int getMedio_provider() {
            return medio_provider;
        }

        public String getOrder_url() {
            return order_url;
        }

        public String getOrder_deeplink() {
            return order_deeplink;
        }

        public String getBook_url() {
            return book_url;
        }
    }

    public class NearbyRestaurant
    {
        public Restaurant restaurant;

        public Restaurant getRestaurant() {
            return restaurant;
        }
    }
}
