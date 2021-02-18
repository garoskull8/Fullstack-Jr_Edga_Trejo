package com.edgar.app.ws.impl;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.edgar.app.ws.shared.dto.UserDto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseService {
	
	public String createUser(int id,UserDto user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore= FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture= dbFirestore.collection("users").document(Integer.toString(id)).set(user);
		
		return "agregado";
	}
	
	public String updateUser(int id,UserDto user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore= FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture= dbFirestore.collection("users").document(Integer.toString(id)).set(user);
		return "actualizado";
	}
	
	public String deleteUser(int id) {
		Firestore dbFirestore= FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult= dbFirestore.collection("users").document(Integer.toString(id)).delete();
		return "eliminado";
	}

}
