package com.ulp.inmobiliariafpestchanker.request;

import com.ulp.inmobiliariafpestchanker.modelo.Clave;
import com.ulp.inmobiliariafpestchanker.modelo.Contrato;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Propietario;
import com.ulp.inmobiliariafpestchanker.modelo.TipoInmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Usuario;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class ApiRetrofit {

  //  private static final String PATH="http://practicastuds.ulp.edu.ar/api/";
  private static final String PATH="http://192.168.100.4:5000/";
 // private static final String PATH="http://192.168.56.1:5000/";
    private static  ServiceInmobiliaria servicioInmobiliaria;

    public static ServiceInmobiliaria getServiceInmobiliaria(){

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        servicioInmobiliaria=retrofit.create(ServiceInmobiliaria.class);

        return servicioInmobiliaria;
    }





    public interface ServiceInmobiliaria{
//http://practicastuds.ulp.edu.ar/api/

        //Propietarios
        @POST("Propietarios/login")
        Call<String> login(@Body Usuario user);

        @GET("Propietarios")
        Call<Propietario> obtenerPerfil (@Header("Authorization") String token);

        @POST("Propietarios/actualizarClave")
        Call<Clave> actualizarClave (@Header("Authorization") String token,@Body Clave clave);



        @POST("Propietarios/emailPedido")
        Call<String> emailPedido (@Body String email);




        @POST("Propietarios/Actualizar")
        Call<Propietario> actualizarPerfil (@Header("Authorization") String token,@Body Propietario p);

        //Inmuebles
        @GET("Inmuebles/ListarInmuebles")
        Call<List<Inmueble>> listarInmuebles (@Header("Authorization") String token);


        @POST("Inmuebles/Registrar")
        Call<Inmueble> registrarInmueble (@Header("Authorization") String token,
                                          @Body Inmueble i);


        @POST("Inmuebles/Actualizar")
        Call<Inmueble> actualizarInmueble (@Header("Authorization") String token,@Body Inmueble i);


        // Obtener Tipo de Inmueble
        @GET("TipoInmuebles/ListarTipoInmuebles")
        Call<List<TipoInmueble>> listarTipoInmueble (@Header("Authorization") String token);

        //Contratos
        @GET("Contratos/ListarContratoInmuebles")
        Call<List<Inmueble>> listarContratoInmuebles (@Header("Authorization") String token);

        @POST("Contratos/obtenerContrato")
        Call<Contrato> obtenerContrato (@Header("Authorization") String token, @Body Inmueble i);


        /*para hacerlo FromForm
        @FormUrlEncoded
        @POST("user/edit")
        Call<User> updateUser(@Field("campo1") String first, @Field("campo2") String last);
        */



    }

}
