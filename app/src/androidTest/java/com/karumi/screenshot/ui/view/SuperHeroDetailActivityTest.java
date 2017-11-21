package com.karumi.screenshot.ui.view;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import com.karumi.screenshot.ScreenshotTest;
import com.karumi.screenshot.SuperHeroesApplication;
import com.karumi.screenshot.di.MainComponent;
import com.karumi.screenshot.di.MainModule;
import com.karumi.screenshot.model.SuperHero;
import com.karumi.screenshot.model.SuperHeroesRepository;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import static com.karumi.screenshot.SuperHeroViewHolderTest.AVENGER;
import static com.karumi.screenshot.SuperHeroViewHolderTest.SUPER_HERO;
import static com.karumi.screenshot.SuperHeroViewHolderTest.givenASuperHero;
import static com.karumi.screenshot.SuperHeroViewHolderTest.givenASuperHeroWithALongDescription;
import static com.karumi.screenshot.SuperHeroViewHolderTest.givenASuperHeroWithALongName;
import static com.karumi.screenshot.SuperHeroViewHolderTest.givenAnAvenger;
import static com.karumi.screenshot.SuperHeroViewHolderTest.givenAnAvengerWithALongDescription;
import static com.karumi.screenshot.SuperHeroViewHolderTest.givenAnAvengerWithALongName;
import static com.karumi.screenshot.ui.view.SuperHeroDetailActivity.SUPER_HERO_NAME_KEY;
import static org.mockito.Mockito.when;

public class SuperHeroDetailActivityTest extends ScreenshotTest {

  @Rule public DaggerMockRule<MainComponent> daggerRule =
      new DaggerMockRule<>(MainComponent.class, new MainModule()).set(
          new DaggerMockRule.ComponentSetter<MainComponent>() {
            @Override public void setComponent(MainComponent component) {
              SuperHeroesApplication app =
                  (SuperHeroesApplication) InstrumentationRegistry.getInstrumentation()
                      .getTargetContext()
                      .getApplicationContext();
              app.setComponent(component);
            }
          });

  @Rule public IntentsTestRule<SuperHeroDetailActivity> activityRule =
      new IntentsTestRule<>(SuperHeroDetailActivity.class, true, false);

  @Mock private SuperHeroesRepository repository;

  @Test
  public void shouldShowsSuperHeroNameDescriptionAndBadge() throws Exception {
    final SuperHero superHero = givenASuperHero();
    configureRepository(superHero);

    final SuperHeroDetailActivity activity = startActivity(superHero.getName());

    compareScreenshot(activity);
  }

  @Test
  public void shouldShowsSuperHeroWithEmptyNameDescription() throws Exception {
    final SuperHero superHero = givenASuperHero(null, null, SUPER_HERO);
    configureRepository(superHero);

    final SuperHeroDetailActivity activity = startActivity(superHero.getName());

    compareScreenshot(activity);
  }

  @Test
  public void shouldShowsAvengerWithEmptyNameDescription() throws Exception {
    final SuperHero superHero = givenASuperHero(null, null, AVENGER);
    configureRepository(superHero);

    final SuperHeroDetailActivity activity = startActivity(superHero.getName());

    compareScreenshot(activity);
  }

  @Test
  public void shouldShowsSuperHeroLongName() throws Exception {
    final SuperHero superHero = givenASuperHeroWithALongName();
    configureRepository(superHero);

    final SuperHeroDetailActivity activity = startActivity(superHero.getName());

    compareScreenshot(activity);
  }

  @Test
  public void shouldShowsSuperHeroLongDescription() throws Exception {
    final SuperHero superHero = givenASuperHeroWithALongDescription();
    configureRepository(superHero);

    final SuperHeroDetailActivity activity = startActivity(superHero.getName());

    compareScreenshot(activity);
  }

  @Test
  public void shouldShowsAvengerNameDescriptionAndNotBadge() throws Exception {
    final SuperHero avenger = givenAnAvenger();
    configureRepository(avenger);

    final SuperHeroDetailActivity activity = startActivity(avenger.getName());

    compareScreenshot(activity);
  }

  @Test
  public void shouldShowsAvengerLongName() throws Exception {
    final SuperHero superHero = givenAnAvengerWithALongName();
    configureRepository(superHero);

    final SuperHeroDetailActivity activity = startActivity(superHero.getName());

    compareScreenshot(activity);
  }

  @Test
  public void shouldShowsAvengerLongDescription() throws Exception {
    final SuperHero superHero = givenAnAvengerWithALongDescription();
    configureRepository(superHero);

    final SuperHeroDetailActivity activity = startActivity(superHero.getName());

    compareScreenshot(activity);
  }

  private void configureRepository(SuperHero superHero) {
    when(repository.getByName(superHero.getName())).thenReturn(superHero);
  }

  private SuperHeroDetailActivity startActivity(String itemName) {
    final Intent intent = new Intent();
    intent.putExtra(SUPER_HERO_NAME_KEY, itemName);
    return activityRule.launchActivity(intent);
  }
}