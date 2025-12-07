// Core domain types matching backend API

export interface User {
  id: number;
  name: string;
  username: string;
  email: string;
  phone: string;
  website: string;
}

export interface Post {
  id: number;
  user: { id: number };
  title: string;
  body: string;
}

export interface Comment {
  id: number;
  post: { id: number };
  name: string;
  email: string;
  body: string;
}

export interface Todo {
  id: number;
  user: { id: number };
  title: string;
  completed: boolean;
}

export interface Album {
  id: number;
  user: { id: number };
  title: string;
}

export interface Photo {
  id: number;
  album: { id: number };
  title: string;
  url: string;
  thumbnailUrl: string;
}

// API Response types
export interface ApiResponse<T> {
  data: T;
  status: number;
}

export interface ApiError {
  message: string;
  status: number;
}
