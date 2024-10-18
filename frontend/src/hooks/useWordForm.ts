import axios from 'axios';
import { WordData } from '../interface/WordData';

const API_URL = "http://localhost:8080/words";

export const saveWord = async (newWordData:WordData) => {
  try {
    const response = await axios.post(API_URL, newWordData);
    return response.data;
  } catch (error) {
    console.error('Erro ao adicionar palavra:', error);
    throw error;
  }
};
