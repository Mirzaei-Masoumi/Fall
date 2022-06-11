package com.fall.masomi.retrofit_api;

import com.fall.masomi.Fal;

import java.util.List;

public class GetFallResponse {
    public String title;
    public String plainText;
    public String htmlText;
    public List<Verse> verses;

    public static class Verse {
        public String id;
        public int vOrder;
        public String text;
    }
}
