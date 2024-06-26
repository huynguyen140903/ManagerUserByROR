Rails.application.routes.draw do
  resources :confirms
  resources :user_details
  resources :successes
  resources :system_errors
  resources :edit_users
  resources :add_users
  resources :list_users
  resources :logins
  resources :articles
  # Define your application routes per the DSL in https://guides.rubyonrails.org/routing.html

  # Reveal health status on /up that returns 200 if the app boots with no exceptions, otherwise 500.
  # Can be used by load balancers and uptime monitors to verify that the app is live.
  get "up" => "rails/health#show", as: :rails_health_check

  # Defines the root path route ("/")
  root 'logins#index'
  get'/list_users', to: 'list_users#index'
  get '/user_detail', to: 'user_details#show'
  get '/add_users', to: 'add_users#index'
  get '/edit_users', to: 'edit_users#index'

end
