package com.github.csy;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class LocaleUtilsTest {

    LocaleUtils localeUtilsl;

    @Before
    public void setUp() throws Exception {
        localeUtilsl = new LocaleUtils();

    }

    @Test
    public void should_input_null_toLocale_return_null() {
        //given
        //when
        //then
        assertThat(localeUtilsl.toLocale(null)).isNull();
    }

    @Test
    public void should_input_empty_toLocale_return_empty() {
        //given
        //when
        //then
        assertThat(localeUtilsl.toLocale("")).isEqualToComparingFieldByField(new Locale("", ""));
    }

    @Test
    public void should_input_hash_toLocale_return_exception() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("#"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: #");
    }
    @Test
    public void should_input_length_less_than_two_toLocale_return_exception() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("a"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: a");
    }
    @Test
    public void should_input_length_less_than_three_start_with_underline_toLocale_return_exception() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("_a"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: _a");
    }
    @Test
    public void should_input_lengthMoreThanTwo_startWithUnderline_secondIsLowerCase_toLocale_return_exception() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("_aB"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: _aB");
    }
    @Test
    public void should_input_lengthMoreThanTwo_startWithUnderline_thirdIsLowerCase_toLocale_return_exception() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("_Ab"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: _Ab");
    }
    @Test
    public void should_input_lengthMoreThanTwo_startWithUnderline_secondThirdAreLowerCase_toLocale_return_exception() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("_ab"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: _ab");
    }

    @Test
    public void should_input_lengthEqualThree_startWithUnderline_secondThirdAreUpperCase_toLocale_return_value() {
        //given
        //when
        //then
        assertThat(localeUtilsl.toLocale("_AB")).isEqualToComparingFieldByField(new Locale("","AB"));
    }
    @Test
    public void should_input_lengthEqualFour_startWithUnderline_toLocale_return_value() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("_ABC"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: _ABC");
    }
    @Test
    public void should_input_lengthMoreThanFive_startWithUnderline_FourthIsNotUnderline_toLocale_return_value() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("_ABCAD"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: _ABCAD");
    }
    @Test
    public void should_input_lengthMoreThanFive_startWithUnderline_FourthIsUnderline_toLocale_return_value() {
        //given
        //when
        //then
        assertThat(localeUtilsl.toLocale("_AB_AD")).isEqualToComparingFieldByField(new Locale("","AB","AD"));

    }

    @Test
    public void should_input_lengthEqualThree_ISO639LanguageCode_toLocale_return_value() {
        //given
        //when
        //then
        assertThat(localeUtilsl.toLocale("abc")).isEqualToComparingFieldByField(new Locale("abc"));
    }
    @Test
    public void should_input_lengthEqualTwo_ISO639LanguageCode_toLocale_return_value() {
        //given
        //when
        //then
        assertThat(localeUtilsl.toLocale("ab")).isEqualToComparingFieldByField(new Locale("ab"));
    }


    @Test
    public void should_input_lengthEqualTwo_SegmentLengthEqualZero_toLocale_return_value() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("AB"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: AB");
    }
    @Test
    public void should_input_lengthMortThanThree_SegmentLengthEqualZero_toLocale_return_value() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("abcd"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: abcd");
    }

    @Test
    public void should_input_lengthMortThanThree_SegmentLengthEqualOne_toLocale_return_value() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("ab_"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: ab_");
    }
    @Test
    public void should_input_SegmentLengthEqualTwo_toLocale_return_value() {
        //given
        //when
        //then
        assertThatThrownBy(() -> { localeUtilsl.toLocale("ab_cd_ef"); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid locale format: ab_cd_ef");
    }

    @Test
    public void should_input_SegmentLengthEqualTwo_matchLanguage_matchCountry_toLocale_return_value() {
        //given
        //when
        //then
        assertThat(localeUtilsl.toLocale("ab_CD")).isEqualToComparingFieldByField(new Locale("ab","CD"));
    }

    @Test
    public void should_input_SegmentLengthEqualTwo_matchNumberAreaCode_toLocale_return_value() {
        //given
        //when
        //then
        assertThat(localeUtilsl.toLocale("ab_123")).isEqualToComparingFieldByField(new Locale("ab","123"));
    }



}