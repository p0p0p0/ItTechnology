package com.safetyhuge.chanlian.safetyhuge.widget;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */

public class NearByInfo extends IInfo{


    /**
     * response : 318
     * message : ok
     * type : [{"name":"服务1","pid":"1"},{"name":"服务2","pid":"1"},{"name":"服务3","pid":"1"},{"name":"求助1","pid":"2"},{"name":"求助2","pid":"2"},{"name":"求助3","pid":"2"}]
     * data : [{"latitude":"36.6679670000","longitude":"117.0464340000","class":"求助","type":"求助1","url":"http://pic.58pic.com/58pic/13/20/61/89B58PIC5Nz_1024.jpg","title":"陪练","detail":"alksdjasdjgkjsdakgjasdklgjklads","money":"10元/分钟","position":"100m","name":"小红"},{"latitude":"36.6780670000","longitude":"117.0464340000","class":"求助","type":"求助2","url":"http://pic.58pic.com/58pic/13/20/61/89B58PIC5Nz_1024.jpg","title":"照顾老年人","detail":"空间啊是倒海翻江卡大煞风景可还是短发;gjlaks;d","money":"5元/小时","position":"50m","name":"小绿"},{"latitude":"36.6790240000","longitude":"117.0686190000","class":"服务","type":"服务1","url":"http://pic.58pic.com/58pic/13/20/61/89B58PIC5Nz_1024.jpg","title":"照顾小朋友","detail":"alskfjalskdfklsgsajdglkalkfgadslk;gjlaks;d","money":"15元/分钟","position":"20m","name":"小强"},{"latitude":"36.6780040000","longitude":"117.0686130000","class":"服务","type":"服务2","url":"http://pic.58pic.com/58pic/13/20/61/89B58PIC5Nz_1024.jpg","title":"照顾小朋友","detail":"alskfjalskdfklsgsajdglkalkfgadslk;gjlaks;d","money":"1元/分钟","position":"61m","name":"小明"}]
     */

    private String response;
    private String message;
    private List<TypeBean> type;
    private List<DataBean> data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class TypeBean {
        /**
         * name : 服务1
         * pid : 1
         */

        private String name;
        private String pid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }
    }

    public static class DataBean implements Parcelable {
        /**
         * latitude : 36.6679670000
         * longitude : 117.0464340000
         * class : 求助
         * type : 求助1
         * url : http://pic.58pic.com/58pic/13/20/61/89B58PIC5Nz_1024.jpg
         * title : 陪练
         * detail : alksdjasdjgkjsdakgjasdklgjklads
         * money : 10元/分钟
         * position : 100m
         * name : 小红
         */

        private String latitude;
        private String longitude;
        @SerializedName("class")
        private String classX;
        private String type;
        private String url;
        private String title;
        private String detail;
        private String money;
        private String position;
        private String name;
        private String medal;
        private String id;
        private String phone;
        private String  uid;
        private String service_status;
        private String lng;

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        private String lat;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMedal() {
            return medal;
        }
        public void setMedal(String medal) {
            this.medal = medal;
        }
        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

       /* @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DataBean dataBean = (DataBean) o;

            if (latitude != null ? !latitude.equals(dataBean.latitude) : dataBean.latitude != null)
                return false;
            if (longitude != null ? !longitude.equals(dataBean.longitude) : dataBean.longitude != null)
                return false;
            if (classX != null ? !classX.equals(dataBean.classX) : dataBean.classX != null)
                return false;
            if (type != null ? !type.equals(dataBean.type) : dataBean.type != null) return false;
            if (url != null ? !url.equals(dataBean.url) : dataBean.url != null) return false;
            if (title != null ? !title.equals(dataBean.title) : dataBean.title != null)
                return false;
            if (detail != null ? !detail.equals(dataBean.detail) : dataBean.detail != null)
                return false;
            if (money != null ? !money.equals(dataBean.money) : dataBean.money != null)
                return false;
            if (position != null ? !position.equals(dataBean.position) : dataBean.position != null)
                return false;
            if (name != null ? !name.equals(dataBean.name) : dataBean.name != null) return false;
            if (medal != null ? !medal.equals(dataBean.medal) : dataBean.medal != null)
                return false;
            return id != null ? id.equals(dataBean.id) : dataBean.id == null;
        }*/

        @Override
        public int hashCode() {
            int result = latitude != null ? latitude.hashCode() : 0;
            result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
            result = 31 * result + (classX != null ? classX.hashCode() : 0);
            result = 31 * result + (type != null ? type.hashCode() : 0);
            result = 31 * result + (url != null ? url.hashCode() : 0);
            result = 31 * result + (title != null ? title.hashCode() : 0);
            result = 31 * result + (detail != null ? detail.hashCode() : 0);
            result = 31 * result + (money != null ? money.hashCode() : 0);
            result = 31 * result + (position != null ? position.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (medal != null ? medal.hashCode() : 0);
            result = 31 * result + (id != null ? id.hashCode() : 0);
            return result;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.latitude);
            dest.writeString(this.longitude);
            dest.writeString(this.classX);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeString(this.title);
            dest.writeString(this.detail);
            dest.writeString(this.money);
            dest.writeString(this.position);
            dest.writeString(this.name);
            dest.writeString(this.medal);
            dest.writeString(this.id);
            dest.writeString(this.phone);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.latitude = in.readString();
            this.longitude = in.readString();
            this.classX = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.title = in.readString();
            this.detail = in.readString();
            this.money = in.readString();
            this.position = in.readString();
            this.name = in.readString();
            this.medal = in.readString();
            this.id = in.readString();
            this.phone = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getService_status() {
            return service_status;
        }

        public void setService_status(String service_status) {
            this.service_status = service_status;
        }
    }
}
