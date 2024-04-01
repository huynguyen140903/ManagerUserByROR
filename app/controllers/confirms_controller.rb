class ConfirmsController < ApplicationController
  before_action :set_confirm, only: %i[ show edit update destroy ]

  # GET /confirms or /confirms.json
  def index
    @confirms = Confirm.all
  end

  # GET /confirms/1 or /confirms/1.json
  def show
  end

  # GET /confirms/new
  def new
    @confirm = Confirm.new
  end

  # GET /confirms/1/edit
  def edit
  end

  # POST /confirms or /confirms.json
  def create
    @confirm = Confirm.new(confirm_params)

    respond_to do |format|
      if @confirm.save
        format.html { redirect_to confirm_url(@confirm), notice: "Confirm was successfully created." }
        format.json { render :show, status: :created, location: @confirm }
      else
        format.html { render :new, status: :unprocessable_entity }
        format.json { render json: @confirm.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /confirms/1 or /confirms/1.json
  def update
    respond_to do |format|
      if @confirm.update(confirm_params)
        format.html { redirect_to confirm_url(@confirm), notice: "Confirm was successfully updated." }
        format.json { render :show, status: :ok, location: @confirm }
      else
        format.html { render :edit, status: :unprocessable_entity }
        format.json { render json: @confirm.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /confirms/1 or /confirms/1.json
  def destroy
    @confirm.destroy!

    respond_to do |format|
      format.html { redirect_to confirms_url, notice: "Confirm was successfully destroyed." }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_confirm
      @confirm = Confirm.find(params[:id])
    end

    # Only allow a list of trusted parameters through.
    def confirm_params
      params.fetch(:confirm, {})
    end
end
