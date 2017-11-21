package com.karumi.screenshot.model;

public class SuperHeroBuilder {
  private String name;
  private String photo;
  private boolean isAvenger;
  private String description;

  public SuperHeroBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public SuperHeroBuilder setPhoto(String photo) {
    this.photo = photo;
    return this;
  }

  public SuperHeroBuilder setIsAvenger(boolean isAvenger) {
    this.isAvenger = isAvenger;
    return this;
  }

  public SuperHeroBuilder setDescription(String description) {
    this.description = description;
    return this;
  }

  public SuperHero createSuperHero() {
    return new SuperHero(name, photo, isAvenger, description);
  }
}