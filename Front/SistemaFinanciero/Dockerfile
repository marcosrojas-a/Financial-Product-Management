# ---- Compilación ----
FROM node:22-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

# ---- Servidor ----
FROM nginx:1.27-alpine
COPY --from=build /app/dist/*/browser /usr/share/nginx/html
EXPOSE 80