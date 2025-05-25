import axios from "axios";

const API_URL = "http://localhost:8080/vehiculos";

export const getVehiculos = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

export const registrarVehiculo = async (vehiculo) => {
  const response = await axios.post(API_URL, vehiculo);
  return response.data;
};