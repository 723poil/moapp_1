# FROM node:18 as builder

# WORKDIR /usr/src/app/react

# COPY ./react/package*.json ./
# RUN npm install

# COPY ./react .

# RUN npm run build

FROM node:18

WORKDIR /usr/src/app

RUN set -xe \
    && apt-get update \
    && apt-get -y install python3-pip
RUN pip install --upgrade pip
RUN pip install tensorflow==2.9.2
RUN pip install Pillow
RUN pip install Image

COPY package*.json ./
RUN npm install

# COPY --from=builder /usr/src/app/react/build /usr/src/app/react/build

# COPY . .

EXPOSE 4500
CMD ["npm", "start"]