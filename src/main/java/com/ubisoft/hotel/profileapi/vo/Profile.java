package com.ubisoft.hotel.profileapi.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author fonsito
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Profile {

    @Id
    @GeneratedValue
    @Column(unique = false, insertable = true, nullable = true, updatable = true, length = 255, scale = 0, precision = 0)
    private Long id;

    @Basic(optional = true)
    @Column(unique = false, insertable = true, nullable = true, updatable = true, length = 255, scale = 0, precision = 0)
    private String name;

    @Basic(optional = true)
    @Column(unique = false, insertable = true, nullable = true, updatable = true, length = 255, scale = 0, precision = 0)
    private String lastPlayedVersion;

    @Basic(optional = true)
    @Column(unique = false, insertable = true, nullable = true, updatable = true, length = 255, scale = 0, precision = 0)
    private Long lastPlayed;

    @Basic(optional = true)
    @Column(unique = false, insertable = true, nullable = true, updatable = true, length = 255, scale = 0, precision = 0)
    private String language;

    @Basic(optional = true)
    @Column(unique = false, insertable = true, nullable = true, updatable = true, length = 255, scale = 0, precision = 0)
    private boolean ageRestricted;

    @Version
    @Column(unique = false, insertable = true, nullable = true, updatable = true, length = 255, scale = 0, precision = 0)
    private long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile id(Long id) {

        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile name(String name) {

        this.name = name;
        return this;
    }

    public String getLastPlayedVersion() {
        return lastPlayedVersion;
    }

    public void setLastPlayedVersion(String lastPlayedVersion) {
        this.lastPlayedVersion = lastPlayedVersion;
    }

    public Profile lastPlayedVersion(String lastPlayedVersion) {

        this.lastPlayedVersion = lastPlayedVersion;
        return this;
    }

    public Long getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Long lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public Profile lastPlayed(Long lastPlayed) {

        this.lastPlayed = lastPlayed;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Profile language(String language) {

        this.language = language;
        return this;
    }

    public boolean getAgeRestricted() {
        return ageRestricted;
    }

    public void setAgeRestricted(boolean ageRestricted) {
        this.ageRestricted = ageRestricted;
    }

    public Profile ageRestricted(boolean ageRestricted) {

        this.ageRestricted = ageRestricted;
        return this;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Profile version(long version) {

        this.version = version;
        return this;
    }

}