/*
 * Copyright (C) 2017 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.screenshot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.karumi.screenshot.model.SuperHero;
import com.karumi.screenshot.model.SuperHeroBuilder;
import com.karumi.screenshot.ui.presenter.SuperHeroesPresenter;
import com.karumi.screenshot.ui.view.SuperHeroViewHolder;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.mockito.Mockito.mock;

public class SuperHeroViewHolderTest extends ScreenshotTest {

  public static final String SUPER_HERO_NAME = "Super Hero Name";
  public static final String SUPER_HERO_DESCRIPTION = "Super Hero Description";
  public static final boolean SUPER_HERO = false;
  public static final boolean AVENGER = true;

  public static SuperHeroViewHolder givenASuperHeroViewHolder() {
    Context context = getInstrumentation().getTargetContext();
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.super_hero_row, null, false);
    return new SuperHeroViewHolder(view, mock(SuperHeroesPresenter.class));
  }

  public static SuperHero givenASuperHeroWithALongDescription() {
    return givenASuperHeroWithALongDescription(SUPER_HERO);
  }

  public static SuperHero givenAnAvengerWithALongDescription() {
    return givenASuperHeroWithALongDescription(AVENGER);
  }
  public static SuperHero givenASuperHeroWithALongDescription(boolean superHero) {
    String superHeroDescription =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
            + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation "
            + "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in "
            + "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
            + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
            + "mollit anim id est laborum.";
    return givenASuperHero(SUPER_HERO_NAME, superHeroDescription, superHero);
  }

  public static SuperHero givenASuperHeroWithALongName(){
    return givenASuperHeroWithALongName(SUPER_HERO);
  }
  public static SuperHero givenAnAvengerWithALongName(){
    return givenASuperHeroWithALongName(AVENGER);
  }

  public static SuperHero givenASuperHeroWithALongName(boolean superHero) {
    String superHeroName =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
            + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation "
            + "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in "
            + "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
            + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
            + "mollit anim id est laborum.";
    return givenASuperHero(superHeroName, SUPER_HERO_DESCRIPTION, superHero);
  }

  public static SuperHero givenAnAvenger() {
    return givenASuperHero(SUPER_HERO_NAME, SUPER_HERO_DESCRIPTION, AVENGER);
  }

  public static SuperHero givenASuperHero() {
    return givenASuperHero(SUPER_HERO_NAME, SUPER_HERO_DESCRIPTION, SUPER_HERO);
  }

  public static SuperHero givenASuperHero(String superHeroName, String superHeroDescription,
      boolean isAvenger) {
    return new SuperHeroBuilder().setName(superHeroName)
        .setPhoto(null)
        .setIsAvenger(isAvenger)
        .setDescription(superHeroDescription)
        .createSuperHero();
  }

  @Test public void showsAnySuperHero() {
    SuperHero superHero = givenASuperHero();
    SuperHeroViewHolder holder = givenASuperHeroViewHolder();

    holder.render(superHero);

    compareScreenshot(holder, R.dimen.super_hero_row_height);
  }

  @Test
  public void showsBadgeWhenIsAvenger() throws Exception {
    final SuperHero superHero = givenASuperHero();
    final SuperHeroViewHolder holder = givenASuperHeroViewHolder();

    holder.render(superHero);

    compareScreenshot(holder, R.dimen.super_hero_row_height);
  }

  @Test
  public void shouldNotShowsBadgeWhenIsAvenger() throws Exception {
    final SuperHero superHero = givenAnAvenger();
    final SuperHeroViewHolder holder = givenASuperHeroViewHolder();

    holder.render(superHero);

    compareScreenshot(holder, R.dimen.super_hero_row_height);
  }

  @Test
  public void showsAvengerWithLongName() throws Exception {
    final SuperHero superHero = givenASuperHeroWithALongName(SUPER_HERO);
    final SuperHeroViewHolder holder = givenASuperHeroViewHolder();

    holder.render(superHero);

    compareScreenshot(holder, R.dimen.super_hero_row_height);
  }
}