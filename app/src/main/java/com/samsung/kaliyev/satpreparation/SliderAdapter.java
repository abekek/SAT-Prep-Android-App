package com.samsung.kaliyev.satpreparation;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images = {
      R.mipmap.sat_logo_round,
      R.mipmap.article_logo,
            R.mipmap.vocab_logo,
            R.mipmap.quiz_logo,
            R.mipmap.resources_logo,
            R.mipmap.university_logo
    };

    public String[] slide_headings = {
      "SAT Preparation by @abekek",
            "Читайте статьи",
            "Учите слова",
            "Тренируйтесь",
            "Скачивайте материалы",
            "Поступайте в топовые университеты"
    };

    public String[] slide_descriptions = {
      "С помощью данного приложения вы облегчите свою подготовку к SAT. Вся информация доступна на русском языке!",
      "Читайте статьи, которые пошагово объяснят вам про SAT и дадут различные стратегии для подготовки. В процессе изучения" +
              " вы также сможете прочитать истории успеха других ребят, которые сдавали SAT",
            "В приложении собраны 500 слов, которые точно пригодятся вам на SAT",
            "Закрепляйте выученные слова при помощи тестов, которые выполнены в игровой форме",
            "Нужны книги или практические тесты? В приложении есть список полезных ресурсов для скачивания",
            "Вы уже хорошо сдали SAT и готовы поступать в университеты? В приложении вы найдете интересующий вас ВУЗ!"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.slideImage);
        TextView slideHeading = view.findViewById(R.id.slideHeading);
        TextView slideDescription = view.findViewById(R.id.slideDescription);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
