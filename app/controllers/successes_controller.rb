class SuccessesController < ApplicationController
  before_action :set_success, only: %i[ show edit update destroy ]

  # GET /successes or /successes.json
  def index
    @successes = Success.all
  end

  # GET /successes/1 or /successes/1.json
  def show
  end

  # GET /successes/new
  def new
    @success = Success.new
  end

  # GET /successes/1/edit
  def edit
  end

  # POST /successes or /successes.json
  def create
    @success = Success.new(success_params)

    respond_to do |format|
      if @success.save
        format.html { redirect_to success_url(@success), notice: "Success was successfully created." }
        format.json { render :show, status: :created, location: @success }
      else
        format.html { render :new, status: :unprocessable_entity }
        format.json { render json: @success.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /successes/1 or /successes/1.json
  def update
    respond_to do |format|
      if @success.update(success_params)
        format.html { redirect_to success_url(@success), notice: "Success was successfully updated." }
        format.json { render :show, status: :ok, location: @success }
      else
        format.html { render :edit, status: :unprocessable_entity }
        format.json { render json: @success.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /successes/1 or /successes/1.json
  def destroy
    @success.destroy!

    respond_to do |format|
      format.html { redirect_to successes_url, notice: "Success was successfully destroyed." }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_success
      @success = Success.find(params[:id])
    end

    # Only allow a list of trusted parameters through.
    def success_params
      params.fetch(:success, {})
    end
end
