package com.gl.tmdb.content.network;

import com.gl.tmdb.content.ApiServices;
import com.gl.tmdb.content.network.responses.ErrorResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by jan.murin on 06-Sep-16.
 */
public class ErrorUtils {

//    public static ErrorResponse parseError(Response<?> response) {
//        Converter<ResponseBody, APIError> converter =
//                ApiServices.retrofit.responseBodyConverter(APIError.class, new Annotation[0]);
//
//        APIError error;
//
//        try {
//            error = converter.convert(response.errorBody());
//        } catch (IOException e) {
//            return new APIError();
//        }
//
//        return error;
//    }
}