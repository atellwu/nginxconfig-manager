package com.yeahmobi.loadbalance_manager.model;

public class Member {

    private String  host;
    private Integer maxFails;
    private String  failTimeout;
    private String  weight;

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getMaxFails() {
        return this.maxFails;
    }

    public void setMaxFails(Integer maxFails) {
        this.maxFails = maxFails;
    }

    public String getFailTimeout() {
        return this.failTimeout;
    }

    public void setFailTimeout(String failTimeout) {
        this.failTimeout = failTimeout;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.failTimeout == null) ? 0 : this.failTimeout.hashCode());
        result = (prime * result) + ((this.host == null) ? 0 : this.host.hashCode());
        result = (prime * result) + ((this.maxFails == null) ? 0 : this.maxFails.hashCode());
        result = (prime * result) + ((this.weight == null) ? 0 : this.weight.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Member other = (Member) obj;
        if (this.failTimeout == null) {
            if (other.failTimeout != null) return false;
        } else if (!this.failTimeout.equals(other.failTimeout)) return false;
        if (this.host == null) {
            if (other.host != null) return false;
        } else if (!this.host.equals(other.host)) return false;
        if (this.maxFails == null) {
            if (other.maxFails != null) return false;
        } else if (!this.maxFails.equals(other.maxFails)) return false;
        if (this.weight == null) {
            if (other.weight != null) return false;
        } else if (!this.weight.equals(other.weight)) return false;
        return true;
    }

}
