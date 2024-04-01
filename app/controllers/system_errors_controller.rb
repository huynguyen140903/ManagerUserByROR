class SystemErrorsController < ApplicationController
  before_action :set_system_error, only: %i[ show edit update destroy ]

  # GET /system_errors or /system_errors.json
  def index
    @system_errors = SystemError.all
  end

  # GET /system_errors/1 or /system_errors/1.json
  def show
  end

  # GET /system_errors/new
  def new
    @system_error = SystemError.new
  end

  # GET /system_errors/1/edit
  def edit
  end

  # POST /system_errors or /system_errors.json
  def create
    @system_error = SystemError.new(system_error_params)

    respond_to do |format|
      if @system_error.save
        format.html { redirect_to system_error_url(@system_error), notice: "System error was successfully created." }
        format.json { render :show, status: :created, location: @system_error }
      else
        format.html { render :new, status: :unprocessable_entity }
        format.json { render json: @system_error.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /system_errors/1 or /system_errors/1.json
  def update
    respond_to do |format|
      if @system_error.update(system_error_params)
        format.html { redirect_to system_error_url(@system_error), notice: "System error was successfully updated." }
        format.json { render :show, status: :ok, location: @system_error }
      else
        format.html { render :edit, status: :unprocessable_entity }
        format.json { render json: @system_error.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /system_errors/1 or /system_errors/1.json
  def destroy
    @system_error.destroy!

    respond_to do |format|
      format.html { redirect_to system_errors_url, notice: "System error was successfully destroyed." }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_system_error
      @system_error = SystemError.find(params[:id])
    end

    # Only allow a list of trusted parameters through.
    def system_error_params
      params.fetch(:system_error, {})
    end
end
